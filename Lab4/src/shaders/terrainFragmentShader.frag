#version 400 core

in vec2 pass_textureCoords;
in vec3 surfaceNormal;
in vec3 toLightVector;
in vec3 toCameraVector;
in float visibility;

out vec4 out_Color;

uniform sampler2D textureSampler;
uniform vec3 lightColour;
uniform vec3 skyColour;

void main (void){

    vec3 unitNormal = normalize(surfaceNormal);
    vec3 unitLightVector = normalize(toLightVector);

	float nDotl = dot(unitNormal,unitLightVector);
	float brightness = max(nDotl,0.2);
	vec3 diffuse = brightness * lightColour;
	vec3 unitVectorToCamera = normalize(toCameraVector);

	out_Color =  vec4(diffuse,1.0) * texture(textureSampler,pass_textureCoords);
		out_Color = mix(vec4(skyColour, 1.0), out_Color, visibility);
}
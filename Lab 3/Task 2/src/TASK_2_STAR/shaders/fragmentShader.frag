// Taken from
// https://ps-group.github.io/opengl/lesson_14

bool PointIsOnTheLeft(vec2 p0, vec2 p1, vec2 p)
{
    vec2 p0p1 = p1 - p0;
    vec2 n = vec2(-p0p1.y, p0p1.x);
    return dot(p - p0, n) > 0.0;
}

bool PointIsInsideTriangle(vec2 p0, vec2 p1, vec2 p2, vec2 p)
{
    return PointIsOnTheLeft(p0, p1, p) &&
           PointIsOnTheLeft(p1, p2, p) &&
           PointIsOnTheLeft(p2, p0, p);
}

void main()
{
    const vec4 RED_COLOR = vec4(1.0, 0.0, 0.0, 1.0);
    const vec4 WHITE_COLOR = vec4(1.0, 1.0, 1.0, 1.0);
    vec2 pos = gl_FragCoord.xy / vec2(100.0) ;

	const vec2 p0 = vec2(5.5, 2.2);
	const vec2 p1 = vec2(6.5, 3.0);
	const vec2 p2 = vec2(6.5, 5.0);

	const vec2 p3 = vec2(7.5, 2.2);
	const vec2 p4 = vec2(6.5, 3.0);
	const vec2 p5 = vec2(6.5, 5.0);

	const vec2 p6 = vec2(5.0, 4.0);
	const vec2 p7 = vec2(6.5, 3.0);
	const vec2 p8 = vec2(8.0, 4.0);

	if (PointIsInsideTriangle(p0, p1, p2, pos)
	    || PointIsInsideTriangle(p4, p3, p5, pos)
	    || PointIsInsideTriangle(p6, p7, p8, pos))
	{
		gl_FragColor = RED_COLOR;
	}
	else
	{
		gl_FragColor = WHITE_COLOR;
	}
}

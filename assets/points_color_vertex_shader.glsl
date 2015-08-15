attribute vec4 a_Position;
attribute vec3 a_Color;
varying vec3 v_Color;
void main() {
	gl_Position = a_Position;
	v_Color = a_Color;
	gl_PointSize = 10.0;
}
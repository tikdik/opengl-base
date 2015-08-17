attribute vec3 a_Position;
attribute vec2 a_TexturePosition;
uniform mat4 u_MVPMatrix;
varying vec2 v_TexturePosition;
void main() {
	gl_Position =  u_MVPMatrix * vec4(a_Position, 1.0);
	v_TexturePosition = a_TexturePosition;
}
precision mediump float;
uniform sampler2D s_Texture;
varying vec2 v_TexturePosition;
varying float v_Diffuse;
void main() {
	gl_FragColor = texture2D(s_Texture, v_TexturePosition) * v_Diffuse;
}
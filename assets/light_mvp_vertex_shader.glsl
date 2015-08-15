attribute vec4 a_Position;
attribute vec2 a_TexturePosition;
uniform mat4 u_MVPMatrix;
varying vec2 v_TexturePosition;
//for light
uniform mat4 u_MVMatrix;
uniform vec3 u_LightPosition;
attribute vec3 a_Normal;
varying float v_Diffuse;
void main() {
	gl_Position =  u_MVPMatrix * a_Position;
	vec3 modelviewPosition = vec3(u_MVMatrix * a_Position);
	vec3 modelViewNormal = vec3(u_MVMatrix * vec4(a_Normal, 0.0));
	vec3 lightVector = normalize(u_LightPosition - modelviewPosition);
	float diffuse = max(dot(modelViewNormal, lightVector), 0.0);
	float distance = length(lightVector);
	diffuse = diffuse * (1.0 / (1.0 + (0.25 * distance * distance)));
	//add env light
	diffuse = diffuse + 0.3;
	v_Diffuse = diffuse;
	v_TexturePosition = a_TexturePosition;
}
#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D u_texture;
varying vec2 v_texCoords;
uniform float u_time;

vec4 mixer();

void main() {
	vec4 color = texture2D(u_texture, v_texCoords);

	gl_FragColor = color;
}

vec4 mixer() {
    return vec4(0.0, 0.0, 1.0, 1.0);
}
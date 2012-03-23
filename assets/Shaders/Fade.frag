#ifdef TEXTURE
uniform sampler2D m_Texture;
varying vec2 texCoord;
#endif

uniform float m_Value;

void main() {
    #ifdef TEXTURE
        vec4 texVal = texture2D(m_Texture, texCoord);
        gl_FragColor = vec4(
                (texVal.r > 1 ? 1 : texVal.r) - m_Value,
                (texVal.g > 1 ? 1 : texVal.g) - m_Value,
                (texVal.b > 1 ? 1 : texVal.b) - m_Value,
                texVal.a);
    #else
        gl_FragColor = vec4(m_Value, m_Value, m_Value, 1);
    #endif
}
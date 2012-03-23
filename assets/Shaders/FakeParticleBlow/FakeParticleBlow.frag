varying vec2 texCoord;
varying vec2 texCoordAni;

uniform vec4 m_BaseColor;

uniform sampler2D m_MaskMap;
uniform sampler2D m_AniTexMap;

void main(){

float Mask = texture2D(m_MaskMap, texCoord).r;
vec3 AniTex = texture2D(m_AniTexMap, vec2(texCoordAni)).rgb;

        gl_FragColor.rgb = m_BaseColor.rgb * Mask * AniTex;
        gl_FragColor.a = Mask;
}
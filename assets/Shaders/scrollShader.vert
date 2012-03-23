uniform mat4 g_WorldViewProjectionMatrix;
uniform float m_ScrollX;
uniform float m_ScrollY;

attribute vec3 inPosition;
attribute vec2 inTexCoord;

varying vec2 texCoord;

    void main(){
        gl_Position = g_WorldViewProjectionMatrix * vec4(inPosition, 1.0);

        texCoord = vec2(inTexCoord[0] + m_ScrollX,
                        inTexCoord[1] + m_ScrollY);
    }
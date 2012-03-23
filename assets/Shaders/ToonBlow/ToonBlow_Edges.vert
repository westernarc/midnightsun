uniform float m_EdgeSize; 

#define ATTENUATION


uniform mat4 g_WorldViewProjectionMatrix;
uniform mat4 g_WorldViewMatrix;
uniform mat3 g_NormalMatrix;
uniform mat4 g_ViewMatrix;

attribute vec3 inPosition;
attribute vec2 inTexCoord;
attribute vec3 inNormal;



void main(){
   vec4 pos = vec4(inPosition, 1.0);
   vec4 normal = vec4(inNormal,0.0);

   normal = normalize(normal);
   pos = pos + normal * m_EdgeSize;
   gl_Position = g_WorldViewProjectionMatrix * pos;
}

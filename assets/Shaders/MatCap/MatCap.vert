uniform mat4 g_WorldViewProjectionMatrix;
uniform mat4 g_WorldViewMatrix;
uniform mat3 g_NormalMatrix;


varying vec2 texCoord;

attribute vec3 inPosition;
attribute vec2 inTexCoord;
attribute vec3 inNormal;

attribute vec4 inTangent;
varying vec3 mat;


  
    varying vec3 vNormal;


#if defined(NORMALMAP)
    uniform vec3 g_CameraPosition;
    uniform mat4 g_WorldMatrix;
#endif 

  varying vec3 vPosition;
  varying vec3 vViewDir;


void main(){

   vec4 pos = vec4(inPosition, 1.0);
   gl_Position = g_WorldViewProjectionMatrix * pos;
   texCoord = inTexCoord;

   vec3 wvPosition = (g_WorldViewMatrix * pos).xyz;
   vec3 wvNormal  = normalize(g_NormalMatrix * inNormal);
   vec3 viewDir = normalize(-wvPosition);


   #ifdef NORMALMAP
     vec3 wvTangent = normalize(g_NormalMatrix * inTangent.xyz);
     vec3 wvBinormal = cross(wvNormal, wvTangent);

     mat3 tbnMat = mat3(wvTangent, wvBinormal * -inTangent.w,wvNormal);
     mat = vec3(1.0) * tbnMat;
     mat = normalize(mat);
     vPosition = wvPosition * tbnMat;
     vViewDir  = viewDir * tbnMat;

   #else
     
     vPosition = wvPosition;
     vViewDir = viewDir;

   #endif
vNormal = wvNormal;


}
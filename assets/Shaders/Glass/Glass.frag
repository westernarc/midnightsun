#import "Common/ShaderLib/Optics.glsllib"
varying vec2 texCoord;
uniform float m_texturesize;
  varying vec3 vPosition;
  varying vec3 vViewDir;

#ifdef NORMALMAP
  uniform sampler2D m_NormalMap;
 uniform float  m_NormalMapPower;    
  varying vec3 mat;
  varying vec3 I;  
  varying vec3 N;   
  varying vec3 worldPos; 
#endif

  varying vec3 vNormal;

#ifdef SPECULAR
  varying vec3 specularColor;
  uniform sampler2D m_MatCapSpecular;
  uniform float m_specularIntensity;
#endif

uniform vec4 m_colorMultiply;
uniform float m_colorIntensity;

    uniform float m_RefPower;
    uniform float m_RefIntensity;
    varying vec3 refVec;
    uniform ENVMAP m_RefMap;

#ifdef CHROMATIC_ABERRATION
varying vec3 refVecG;
varying vec3 refVecB;
#endif

void main(){

vec2 newTexCoord;
newTexCoord = texCoord;

 #ifdef NORMALMAP
      vec3 normalHeight = texture2D(m_NormalMap, newTexCoord).rgb;
   //   vec3 normal = ((normalHeight.xyz  - vec3(0.5))* vec3(2.0));
     vec3 normal = (normalHeight.xyz * vec3(2.0) - vec3(1.0));
      normal = normalize(normal);

    #if defined (NOR_INV_X) && (NORMALMAP) 
    normal.x = -normal.x;
    #endif

    #if defined (NOR_INV_Y) && (NORMALMAP)
    normal.y = -normal.y;
    #endif

    #if defined (NOR_INV_Z) && (NORMALMAP)
    normal.z = -normal.z;
    #endif
 
      #else 
      vec3 normal = vNormal;
    #endif

vec3  vmr = vNormal.xyz;
vec3 coords = (vmr);




    #if defined (NORMALMAP)
vec3  normalz = mat.xyz*normal.xyz;
vec4 refGet = Optics_GetEnvColor(m_RefMap, (refVec - normalz*m_NormalMapPower));
#ifdef CHROMATIC_ABERRATION
refGet.g = Optics_GetEnvColor(m_RefMap, (refVecG - normalz*m_NormalMapPower)).g;
refGet.b = Optics_GetEnvColor(m_RefMap, (refVecB - normalz*m_NormalMapPower)).b;
#endif 
    #if defined (SPECULAR) 
specularColor = texture2D(m_MatCapSpecular, vec2((coords*vec3(0.495) + vec3(0.5))+(normalz)*m_NormalMapPower).xy).rgb;
#endif

#else
 vec4 refGet = Optics_GetEnvColor(m_RefMap, refVec);
#ifdef CHROMATIC_ABERRATION
refGet.g = Optics_GetEnvColor(m_RefMap, refVecG).g;
refGet.b = Optics_GetEnvColor(m_RefMap, refVecB).b;
#endif  
#if defined (SPECULAR)
    specularColor = texture2D(m_MatCapSpecular, vec2(coords*vec3(0.495) + vec3(0.5)).xy).rgb;

#endif
#endif
 

vec3 color = refGet.xyz * m_colorMultiply.xyz*m_colorIntensity;
 
#ifdef SPECULAR
    gl_FragColor.rgb = color+specularColor*m_specularIntensity;
#else
    gl_FragColor.rgb = color;
#endif
    gl_FragColor.a = m_colorMultiply.w;

}

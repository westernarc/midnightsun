varying vec2 texCoord;
uniform float m_texturesize;
  varying vec3 vPosition;
  varying vec3 vViewDir;

  uniform sampler2D m_DiffuseMap;

#ifdef NORMALMAP
  uniform sampler2D m_NormalMap;
 uniform float  m_NormalMapPower;    
  varying vec3 mat;
  varying vec4 refVec;
  varying vec3 I;  
  varying vec3 N;   
  varying vec3 worldPos; 


#endif
  varying vec3 vNormal;
  varying vec3 diffuseColor;

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
diffuseColor = texture2D(m_DiffuseMap, vec2((coords*vec3(0.495) + vec3(0.5))+(normalz)*m_NormalMapPower).xy).rgb;
#else
    diffuseColor = texture2D(m_DiffuseMap, vec2(coords*vec3(0.495) + vec3(0.5)).xy).rgb;
//    diffuseColor = (diffuseColor - vec3(0.5, 0.5, 0.5) * 2.0);
#endif
    

    gl_FragColor.rgb = diffuseColor;
    gl_FragColor.a = 1.0;

}

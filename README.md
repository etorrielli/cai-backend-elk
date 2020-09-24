# cai-backend
## Consultas disponibles:
* devolver todos los accidentes ocurridos entre 2 fechas dadas
  * Test sin index: 21 seg
  * Test con index - db.getCollection('accident').createIndex({"Start_Time":"text"}): 3 seg

* determinar las condiciones más comunes en los accidentes (Weather_Condition - Sunrise_Sunset)
  * Test sin index: 5 seg
  * Test con index - db.getCollection('accident').createIndex({Weather_Condition: 1}): 5 seg
  * Test con index - db.getCollection('accident').createIndex({Sunrise_Sunset: 1}): 5 seg
  * Test con index - db.getCollection('accident').createIndex({Sunrise_Sunset: 1, Weather_Condition: 1}): 5 seg
  
* dado un punto geográfico y un radio (expresado en kilómetros) devolver todos los accidentes ocurridos dentro del radio.
* devolver los 5 puntos más peligrosos (definiendo un determinado radio)
* obtener la distancia promedio desde el inicio al fin del accidente


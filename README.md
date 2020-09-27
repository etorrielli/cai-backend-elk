# cai-backend
## Consultas disponibles:
1. Devolver todos los accidentes ocurridos entre 2 fechas dadas
    * Test sin index: 21 seg
    * Test con index - db.getCollection('accident').createIndex({"Start_Time":"text"}): 3 seg

2. Determinar las condiciones más comunes en los accidentes
    * (Total por State)
        * Test sin index: 5 seg
        * Test con index - db.getCollection('accident').createIndex({State: 1}): 5 seg
    * (Weather_Condition - Sunrise_Sunset)
        * Test sin index: 5 seg
        * Test con index - db.getCollection('accident').createIndex({Weather_Condition: 1}): 5 seg
        * Test con index - db.getCollection('accident').createIndex({Sunrise_Sunset: 1}): 5 seg
        * Test con index - db.getCollection('accident').createIndex({Sunrise_Sunset: 1, Weather_Condition: 1}): 5 seg
  
3. Dado un punto geográfico y un radio (expresado en kilómetros) devolver todos los accidentes ocurridos dentro del radio.
4. Devolver los 5 puntos más peligrosos (definiendo un determinado radio)
5. Obtener la distancia promedio desde el inicio al fin del accidente


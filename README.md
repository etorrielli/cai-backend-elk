# cai-backend
## Consultas disponibles:
1. Devolver todos los accidentes ocurridos entre 2 fechas dadas
    * endpoint: http://localhost:9091/api/accident/elk/datefrom/{from}/dateto/{to}

2. Determinar las condiciones más comunes en los accidentes
    * endpoint: http://localhost:9091/api/accident/elk/top-states
      
3. Dado un punto geográfico y un radio (expresado en kilómetros) devolver todos los accidentes ocurridos dentro del radio.
    * endpoint: http://localhost:9091/api/accident/elk/lng/{lng}/lat/{lat}/radius/{radiusKm}

4. Devolver los 5 puntos más peligrosos (definiendo un determinado radio)
    * endpoint: http://localhost:9091/api/accident/elk/top-dangerous-points

5. Obtener la distancia promedio desde el inicio al fin del accidente
    * endpoint: http://localhost:9091/api/accident/elk/avg-distance

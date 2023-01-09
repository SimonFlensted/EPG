#Mine tanker omkring den nuværende datamodel:
- Som udvikler synes jeg den nuværende datamodel er besværlig at arbejde med, og dataen unødigt kompleks. Som provider af dataen tænker jeg heller ikke det kan være den mest oplagte måde at formidle dataen videre. Der er desuden en del redundant data i form af titlerne der går igen, og skulle et fyldt tv program pakkes ind i modellen ville den hurtigt blive uoverskuelig. Dette gør det heller ikke nemmere at debugge eventuelle fejl i dataen.

#Forslag til en anden version:
```json
{
	"2023-01-01": [
		{
			"program": "Nyhederne"
			"schedules": [
				{
					"start": "06:00:00"
					"end": "10:00:00"
				},
				{
					"start": "21:00:00"
					"end": "21:30:00"
				}
			]
		}
		{
			"program": "Dybvaaaaaad"
			"schedules": [
				{
					"start": "10:00:00"
					"end": "10:30:00"
				},
				{
					"start": "10:30:00"
					"end": "11:00:00"
				}
			]
		}		
	],
	"2023-01-02": [
		{
			"program": "Nybyggerne"
			"schedules": [
				{
					"start": "12:00:00"
					"end": "13:00:00"
				}
			]
		}
		{
			"program": "Fodbold"
			"schedules": [
				{
					"start": "20:00:00"
					"end": "22:30:00"
				}
			]
		}		
	]
}
```
- Jeg mener ovenstående datamodel giver et bedre overblik, er lettere at læse, og dermed nemmere at arbejde med og fejlsøge i.
Med dette datoformat har man nu mulighed for mere præcist at angive hvornår et program bliver sendt, og dermed åbne op for en tv-guide som strækker sig længere end en uge ad gangen.
- Koden der skal til for at lave et lignende "human readable format" vil ligeledes blive mere overskuelig og lettere at arbejde. Her forestiller jeg mig at man kan deserialiserer json direkte til et map bestående af en dato som mapper til en liste af programmer, og så lave en metode til at skrive det pænt ud.

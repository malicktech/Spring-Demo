# Micro Services with Spring Cloud

Application distribuée basée sur les micros services avec Spring Cloud
Gère les ordres d'achat ou de vente correspodants a des sociétés cotée en bourse


## inspired by : 
		Getting started with Spring Cloud by Josh Long
		https://youtu.be/SFDYdslOvu8

		
## Architechture

![](https://github.com/diop-malick/Spring-Demo/tree/master/Demo2-SpringCloud/Archi.PNG)


elle montre :
 
- Comment mettre en oeuvre un micro service
- Comment déployer le micro service en plusieurs instances
- Comment Créer un micro service de configuration qui permet de centraliser la configuration des micro services.
- Comment Créer créer un service d'enregistrement de micro service qui permet de publier et de découvrir les micro service de l'architecture distribuée
- Comment Créer un service proxy client qui permet de définir une passerelle vers les autres micro services.
- Comment l'équilibrage de charges est géré par le service Proxy
- Comment faire communiquer les micro services d'une manière asynchrone en utilisant le protocole AMQP de Brocker Rabbit MQ.
- et bien d'autres


## Modules

	- eureka Registration service	: service d'enregistrement, annaire des MS, contient leur noms, ip 
	- Configuration service			: centralise la config de l'ensemble des MS
	- Proxy Service  				: sert d'interface entre le client HTTP et les micros service
	- MS societe					: service pour gérer les sociétés cotées en bourse 
	- MS ordre						: pour gérer les ordres d'achat ou de vente d'action


## Outils & Technos :

	- serveur Rabbit MQ (Protocole AMPQ) : broker le middleware, recois et diffuse les messages aux instances de micro services concernés
	- JDK 8
	- Spring Boot
	- Spring Tools Suite




### access :
 
	start config service  : propiété d'un service 
		localhost:8888/societe-service/master

	start eureka service : localhost:8761
	
	run multiple instances of micro service  :  
		1. run -> run configiration -> add in tab arguments : --server.port=82 
		repeat operation ifor post 81 & 86 for example
		or
		generate jar and use cmd : start java -jar prjectname-0.0.1.jar --server.port=82 
		
	start proxy service : localhost:9999
	 
	to refresh micro service configuration : 
		Post Request to -> localhost:81/refresh
		
	request to : 
		localhost:9999/societe-service/sociestes
		localhost:9999/societe-service/societes?page=0&size=3&sort=nom,desc
		localhost:9999/societe-service/search/societesByKey?key=%25A%25&page=0&size=2
		localhost:9999/names
		
	les requêttes succesives seront traitées à tour de rôle par les différents instances de services déployé sur les différentes machines.


	

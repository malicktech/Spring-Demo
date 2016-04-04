# Cloud Micro Services with Spring Cloud

Application distribuée basé sur les micros service avec Spring Cloud
gère les ordres d'achat ou de vente corespodants aux sociétés cotée en bourse

un ms pour gérer les sociétés cotées en bourse
un ms pour gérer les ordres d'achat ou de vente d'action

inspired by : 
Getting started with Spring Cloud by Josh Long
https://youtu.be/SFDYdslOvu8

## Features

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

MS societe					:  service de gestion des societes 
MS ordre					: service de gestion des ordre

eureka Registration service	: service d'enregistrement, annaire des MS, contient leur noms, ip 
Configuration service		: centralise la config de l'ensemble des MS
Proxy Service  				: sert d'interface entre le client HTTP et les micro service

 	centraliser la configuration des applications des différents environnements.

## Technos :

serveur Rabbit MQ (Protocole AMPQ) : broker le middleware, recois et diffuse les messages aux instances de micro service concerné

pom maven :

restrepository : expose interface Jpa REPOSITORUT COME éTANT  UN SERICE

todo / CHANGE RESTREPOSITORY to 2 layer 
actuator : 	get micro services metrics
stream rabbit : pour utiliser rabbit MQ
eureka discovery : pour contacter le service d'neregistrement 


### access :
 
	localhost:8080/societes
	localhost:8080/societes?page=0&size=3&sort=nom,desc
	localhost:8080/societes/search/societesByKey?key=%25A%25&page=0&size=2

	service conf : 
		propiété d'un service : http://localhost:8888/societe-service/master

	to refresh micro service configuration : 
		Post Request in http://localhost:8080/refresh 

### manips

	run multiple instances of micro service  :  
		1. run -> run configiration -> add in tab arguments : --server.port=82 
		repeat operation ifor post 83 & 84 for example
		or
		generate jar and use cmd : start java -jar prjectname-0.0.1.jar --server.port=82 

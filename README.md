# PROJET JAVA SaferChat - BUT 2A Semestre 4

<p align="center">
  <a href="https://github.com/NathanArkov/privateChatJava/fork" target="_blank">
    <img src="https://img.shields.io/badge/Fork%20on-GitHub-success?style=flat" alt="fork on github" />
  </a>
  <a href="https://github.com/NathanArkov" target="_blank">
    <img src="https://img.shields.io/badge/Follow%20me-GitHub-success?style=flat" alt="follow me" />
  </a>
</p>

Projet réalisé en binôme avec Nathan ROLET (  <a href="https://github.com/Nathan43005" target="_blank"><img src="https://img.shields.io/badge/Nathan43005-GitHub-informational?style=flat" alt="Nathan43005" /></a> )

## Sujet

- Création d'une application de messagerie cryptée en Java.
- Utilisation de JavaFX et de l'algorithme AES.

## Fonctionnalités
- Envoi de messages non cryptés d'un client à un serveur
- Interface graphique de connection
- Logique de connection et de création de compte
- Interface graphique d'ajout de contact

# PROBLÈMES CONNUS
- Les messages ne sont pas cryptés
- Les messages ne s'envoient que lorsqu'on clique sur un contact dans la liste (donc au lancement d'un thread Client), sinon ils se stockent dans une queue
- Les messages ne sont pas affichés dans l'interface graphique

## TODO
- Interface graphique de messagerie
- Cryptage des messages
- Sauvegarde des conversations

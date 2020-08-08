# two-phase-commit-implementation-java
Protocole de validation en deux phases Dans le traitement des transactions, les bases de données et les réseaux informatiques, le protocole de validation en deux phases (2PC) est un type de protocole d'engagement atomique (ACP). C'est un algorithme distribué qui coordonne tous les processus qui participent à une transaction atomique distribuée pour savoir s'il faut valider ou abandonner (annuler) la transaction (c'est un type spécialisé de protocole de consensus).

# Phase de demande de validation
ou phase de vote
1. Le coordinateur envoie une requête pour valider le message à toutes les processus et attend jusqu'à ce qu'il ait reçu une réponse de toutes les processus.
2. Les processus exécutent la transaction jusqu'au point où il leur sera demandé de s'engager. Ils écrivent chacun une entrée dans leur journal d'annulation et une entrée dans leur journal de rétablissement.
3. Chaque processus répond par un message d'accord (la processus vote Oui pour s'engager), si les actions de la processus ont réussi, ou un message d'abandon (la processus vote Non, ne pas s'engager), si la processus subit un échec qui rendra impossible de s'engager .

# Phase de validation


# Si le coordinateur a reçu un message d'accord de tous les processus pendant la phase de demande de validation:

1. Le coordinateur envoie un message de validation à tous les processus.
2. Chaque processus termine l'opération et libère tous les verrous et ressources détenus pendant la transaction.
3. Chaque cohorte envoie un accusé de réception au coordinateur.
4. Le coordinateur termine la transaction lorsque tous les accusés de réception ont été reçus.

# Échec

Si une cohorte vote Non pendant la phase de demande de validation (ou si le délai d'expiration du coordinateur expire):
1. Le coordinateur envoie un message de retour arrière à tous les processus.
2. Chaque processus annule la transaction à l'aide du journal des annulations et libère les ressources et les verrous détenus pendant la transaction.
3. Chaque processus envoie un accusé de réception au coordinateur.
4. Le coordinateur annule la transaction lorsque tous les accusés de réception ont été reçus.


# l'algorithme 

- le client demande d'voir une operation ( ajouter 50 au variable "a")
-Le coordinateur difffuser au tous les processus pour voter 
-les processus sont votent et envoyer les vote au coordinateur .
-d'apres avoir le client recçoit les reponses des processus il fait la decision finale et le diffuser au processus et le client .
- apres le client faire l'action s'il a recus l'accord detraite l'action ou non.

# Exemple d’Exécution :

Tout d'abord, nous exécuter la classe "Client", puis la classe " coordinateur", puis nous exécutons la class "Proc" deux Fois pour crée deux processus pour faire le vote.
Tout d'abord, le client demande au coordinateur de répondre afin d'effectuer une opération, et ce dernier envoie à son tour un message aux tous les processus pour voter pour l'acceptation ou le rejet Dans le cas de tous les processus ayant voté, il accepte la demande dans le cas de tous les processus acceptent la demande, contrairement à si un des processus a refus la demande, la demande sera rejetée ou cas de retardée de 15 secondes dans la réponse

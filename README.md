# SMSReader Assignment

## Requirement
Assuming we are building an app, the app requires the user's permission to read text messages from the phone. Can you write a program that would ask the user for permission and once granted read the incoming messages from the user's phone in the background and save it to the server? Rules are: 

1) Messages read should have at least an amount and date in it.
2) We should not be reading the user's personal message. 

Also build a screen where we can display user amount and time read from messages in list form sorted by latest message first.

![Alt text](/Screenshot.jpeg?raw=true "Optional Title")

## Technology Used

|         Category       | Language/Pattern/Library|
| ---------------------- | ----------------------- |
| Language               | Kotlin                  |
| Architectural Pattern  | MVVM                    |
| Networking             | Retrofit                |
| Dependency Injection   | Koin                    |
| Background Job         | WorkManager             |
| Database               | Firestore               |

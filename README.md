![MBio @ ISCTE](docs/images/mbio_at_iscte.png)

# Workshop - Building microservices with Kotlin

In this repository you will find projects to illustrate how we can move from building microservices in Java to Kotlin.

In these projects and workshop we intend to highlight the main differences and benefits of using Kotlin vs Java to build
microservices.

We have a set of microservices with clear separate responsibilities with each one exposing an API as follows:

- [dealers](./dealers) (Java) - a dealer API with information about dealers.
- [vehicles](./vehicles) (Kotlin) - a vehicle API with information about cars.
- [store-java](./store-java) (Java) - a store API that corresponds to show-room of cars that are available for selling
  on dealers.
- [store-kotlin](./store-kotlin) (Kotlin) - the same store API as [store-java](./store-java) but implemented in Kotlin.

Note that in a real world these projects would be in different repositories. In this scenario we just placed them all
under the same repository to avoid unnecessary complexity for the workshop.

## Requirements

In order for you to build and execute the projects in this repository you need to have the following installed on your
computer:

- Java 17

In order to install Java you have several options since there are several distributions available, for example:

- [Temurin Distribution](https://adoptium.net/)
- [Correto Distribution](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)

## Build & Run

We prepared a Makefile to help building and running your project.

**Run Dealers (Java)**

```bash
make run-dealers
```

**Run Vehicles (Kotlin)**

```bash
make run-vehicles
```

**Run Store (Java)**

```bash
make run-store-java
```

**Run Store (Kotlin)**

```bash
make run-store-kotlin
```

You can also use your favourite IDE to run your project 😉.

## What to do?

During the workshop we will explain the code and help understand the main differences between Java and Kotlin, and also
introduce cool features from Kotlin, in order for you to get familiarized with the code.

After getting in touch with the code, and get the services up and running you should now be ready to role and get your
hands "dirty", and hopefully trilled to explore the wonderful world of Kotlin 🪄. Try to complete the questions that we
have in our code and have fun. If you need help fell free to ask.

You can find the questions by searching for the `TODO` keyword on the files.

---

Date: 27th of April, 16:00

Topic: Building microservices with Kotlin

Duration: 1:30 (Q&A)
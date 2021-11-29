# Welcome

## Roots

| Root ID  | Name                         |
| ---------| ---------------------------- |
| `root-0` | example-0                    |
| `root-1` | example-1                    |
| `root-2` | example-2                    |
| `root-3` | example-3                    |
| `root-4` | example-4                    |

These values are used in the code and in the following examples too.

## Requirements

The project requires [Java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) or
higher.

The project makes use of Gradle and uses
the [Gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html), which means you don't need Gradle
installed.

## Useful Gradle commands

The project makes use of Gradle and uses the Gradle wrapper to help you out carrying some common tasks such as building
the project or running it.

### List all Gradle tasks

List all the tasks that Gradle can do, such as `build` and `test`.

```console
$ ./gradlew tasks
```

### Build the project

Compiles the project, runs the test and then creates an executable JAR file

```console
$ ./gradlew build
```

Run the application using Java and the executable JAR file produced by the Gradle `build` task. The application will be
listening to port `8080`.

```console
$ java -jar build/libs/play-spring-boot.jar
```

### Run the tests

There are two types of tests, the unit tests and the functional tests. These can be executed as follows.

- Run unit tests only

  ```console
  $ ./gradlew test
  ```

- Run functional tests only

  ```console
  $ ./gradlew functionalTest
  ```

- Run both unit and functional tests

  ```console
  $ ./gradlew check
  ```

### Run the application

Run the application which will be listening on port `8080`.

```console
$ ./gradlew bootRun
```

## API

Below is a list of API endpoints with their respective input and output. Please note that the application needs to be
running for the following endpoints to work. For more information about how to run the application, please refer
to [run the application](#run-the-application) section above.

### Store Readings

Endpoint

```text
POST /values/store
```

Example of body

```json
{
  "rootId": <rootId>,
  "exampleValues": [
    {
      "time": <time>,
      "value": <value>
    }
  ]
}
```

Parameters

| Parameter      | Description                                           |
| -------------- | ----------------------------------------------------- |
| `rootId`       | One of the root id listed above                       |
| `time`         | The date/time (as epoch)                              |
| `value`        | Any BigDecimal                                        |


The following POST request, is an example request using CURL, sends the readings shown in the table above.

```console
$ curl \
  -X POST \
  -H "Content-Type: application/json" \
  "http://localhost:8080/values/store" \
  -d '{"rootId":"root-10","exampleValues":[{"time":1606636800,"value":0.0503},{"time":1606636860,"value":0.0621},{"time":1606636920,"value":0.0222},{"time":1606636980,"value":0.0423},{"time":1606637040,"value":0.0191}]}'
```

The above command does not return anything.

### Get Stored Readings

Endpoint

```text
GET /values/read/<rootId>
```

Parameters

| Parameter      | Description                              |
| -------------- | ---------------------------------------- |
| `rootId` | One of the smart meters' id listed above |

Retrieving readings using CURL

```console
$ curl "http://localhost:8080/values/read/root-0"
```

Example output

```json
[
  {
    "time": "2020-11-29T08:00:00Z",
    "value": 0.0503
  },
  {
    "time": "2020-11-29T08:01:00Z",
    "value": 0.0621
  },
  {
    "time": "2020-11-29T08:02:00Z",
    "value": 0.0222
  },
  {
    "time": "2020-11-29T08:03:00Z",
    "value": 0.0423
  },
  {
    "time": "2020-11-29T08:04:00Z",
    "value": 0.0191
  }
]
```
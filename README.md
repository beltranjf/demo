# Business case for text transformations
## Introduction
This is a Spring Boot project that works as a tech demo for doing text transformations through Spring annotations.

## Pre-requisites
1. Java ([For windows](https://www.java.com/es/download/ie_manual.jsp), [for Mac](https://www.java.com/en/download/help/mac_install.html), [for Linux](https://www.java.com/en/download/help/linux_x64_install.html))
2. [Maven](https://maven.apache.org/install.html)

## Running this project
1. Download it `git clone https://github.com/beltranjf/demo.git`
2. Navigate to the root folder `cd demo`
3. Execute `./mvn clean install`
4. Execute `./mvn spring-boot:run`

## Executing text transformations
1. Either through cURL or Postman send a `POST` request to `http://localhost:8080/transform` sending a JSON body like the following example
```
{
    "elements": [
        {
            "value": "This is just Βρίσκεστε στην αρχική σελίδα του: Δημόσιου!",
            "transformers": [
                {
                    "group": "group1",
                    "transformerId": "t1",
                    "parameters": [
                        "!"
                    ]
                }
            ]
        }
    ]
}
```
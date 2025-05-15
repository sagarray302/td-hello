# td-hello
This repo focus on providing a default template for new Talkdesk applications using Spring Boot Web with Kotlin.

The main goal was to provide an opinionated template that follows the main guidelines from the [Application Maturity Model](https://github.com/Talkdesk/resilience-engineering/blob/main/docs/application_maturity_model.md). We're hoping that it may help building more consistent applications across the company.

More details can be found at [Confluence](https://talkdesk.atlassian.net/wiki/spaces/SRE/pages/3775726482/TSBS+Web)

## Contribution

Everyone is encouraged to contribute to this project, and if you decide to do so just make sure to open your PRs you can follow our contribution guideline in the confluence:

[Confluence](https://talkdesk.atlassian.net/wiki/spaces/SRE/pages/3589472576/Contribution+Spring+boot+starter)

## Functionalities

* [Logging](https://talkdesk.atlassian.net/wiki/spaces/SRE/pages/3641836072/TSBS+Logging)
* [Opentracing Jaeger](https://talkdesk.atlassian.net/wiki/spaces/SRE/pages/3640853368/TSBS+%3A%3A+Opentracing+Jaeger)
* [Prometheus](https://talkdesk.atlassian.net/wiki/spaces/SRE/pages/3641770495/TSBS+%3A%3A+Prometheus)
* [Feature Flag](https://talkdesk.atlassian.net/wiki/spaces/SRE/pages/3641344527/TSBS+%3A%3A+Feature+Flag)
* [JWT Validation](https://talkdesk.atlassian.net/wiki/spaces/SRE/pages/3641934237/TSBS+%3A%3A+JWT+Validation)
* [Authz Validation](https://talkdesk.atlassian.net/wiki/spaces/SRE/pages/3776021681/TSBS+%3A%3A+Authz+Validation)
* [Error Notifier](https://talkdesk.atlassian.net/wiki/spaces/SRE/pages/3640558995/TSBS+%3A%3A+Error+Notifier)
* [HTTP Exception Handler](https://talkdesk.atlassian.net/wiki/spaces/SRE/pages/3765306122/TSBS+%3A%3A+HTTP+Exception+Handler)

## Code Build

`./gradlew clean build`


## Code formatting

To format the code just execute:

`./gradlew ktlintCheck`

`./gradlew ktlintFormat`

## Test

`./gradlew test`

## Meza Configuration

### Template vars
- "command": "['./entrypoint.sh']"

### Env vars
- "MANAGEMENT_ENDPOINT_HEALTH_PROBES_ENABLED": "true"
- "MANAGEMENT_SERVER_PORT": "8080"
- "NEW_RELIC_AGENT_ENABLED": "true",
- "NEW_RELIC_APP_NAME": "td-hello",
- "NEW_RELIC_LICENSE_KEY": "<YOUR_NEWRELIC_LICENSE_KEY>"

# Pipeline

## Notifications

By default, all notifications are sent to `service-templates-pipelines`. Nevertheless, this channel should be updated to one belonging to the code owners.# td-hello

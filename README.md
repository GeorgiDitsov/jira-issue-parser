# jira-issue-parser

A RESTful Web Service which obtains data from JIRA and persists it in both XML and JSON files.

## Configuration

- This service generates two types of file formats for fetched data. The file format can be set in `src/main/resources/application.yml`:

  - For generating JSON files:

    ```yaml
    jira-issue-parser:

    	...

    	file-format: json # enter json|xml for parsing issue either in json or xml format

    	...

    ```

  - For generating XML files:

    ```yaml
    jira-issue-parser:

    	...

    	file-format: xml # enter json|xml for parsing issue either in json or xml format

    	...

    ```

## How to use

When you run it on your local machine, test it with `curl`. By default, service is running on `port 8080`.

```
curl http://localhost:8080/rest/api/v1/{jiraquery} --output {filename}
```

- `jiraquery` - Jira Query Language based query, for example:

  ```
  issuetype in (Bug, Documentation, Enhancement) and updated > startOfWeek()
  ```

- `filename` - name of the file where the output of the request will be stored.

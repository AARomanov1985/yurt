# Yurt - XML analyzer

This tool is able to analyze an XML file and return basic metrics.

## Usages:

### Feature 1: start an analysis

HTTP request example

```
curl -i -X POST \
-H "Content-Type:application/json" \
-d \
'{
"url": "https://bitbucket.org/group9nl/java-assessment-base/raw/2c65f4920b0b89eeebc817d03c63c1154f4927ac/example-data/arabic-posts.xml"
}' \
'http://localhost:8080/analyse'

```

Example response of the analysis result:

```json
{
  "id": "1"
  // as long as it is an unique ID
}
```

return 202 code

### Feature 2: Retreive a summary of an analyze

HTTP request example

```
curl --request GET \
  --url 'http://localhost:8080/analyse/1' \
```

Example response of the analysis result:

```json
{
  "uid": 1,
  "analyseDate": "2021-10-10",
  "state": "Finished",
  "failedSummary":"",
  "analyseTimeInSeconds": 1,
  "details": {
    "firstPost": "2015-07-14",
    "lastPost": "2015-09-14",
    "totalPosts": 80,
    "totalAcceptedPosts": 7,
    "avgScore": 2.98
  }
}
```

### Feature 3: Return all rows

HTTP request example

```
curl --request GET \
  --url 'http://localhost:8080/analyse/all' \
```

Example response of the analysis result:

```json
[
  {
    "uid": 1,
    "analyseDate": "2021-10-10",
    "state": "Finished",
    "failedSummary": "",
    "analyseTimeInSeconds": 1,
    "details": {
      "firstPost": "2015-07-14",
      "lastPost": "2015-09-14",
      "totalPosts": 80,
      "totalAcceptedPosts": 7,
      "avgScore": 2.98
    }
  },
  {
    "uid": 2,
    "analyseDate": "2021-10-10",
    "state": "Finished",
    "failedSummary": "",
    "analyseTimeInSeconds": 0,
    "details": {
      "firstPost": "2015-07-14",
      "lastPost": "2015-09-14",
      "totalPosts": 80,
      "totalAcceptedPosts": 7,
      "avgScore": 2.98
    }
  }
]
```

### Feature 4: remove everything of an analysis

HTTP request example

```
curl -X DELETE \
  --url 'http://localhost:8080/analyse/1' \
```

```json
{
  "message": "success"
}
```

### Get a docker container

Container is available in the docker hub:
https://hub.docker.com/repository/docker/aaromanov1985/yurt

```
docker pull aaromanov1985/yurt:latest
```

### How to run a container

```
docker run -d -p 8080:8080 aaromanov1985/yurt:latest
```

With RAM limit:

```
docker run -d -p 8080:8080 -m 512m aaromanov1985/yurt:latest
```

### How to build a container

```
docker build -t aaromanov1985/yurt:latest .
```


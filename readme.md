#QA Candidate Assignment

###Project structure
`main.java` - contains framework and page objects

`test.java` - contains tests

###Running tests

* Run the tests with `mvn test`
* Default browser is Chrome, to run on Firefox set environment variable `mvn -Denv.browser=firefox test`
* To run tests on docker setup selenium grid and environment `mvn -Denv.browser=firefox_docker test` or `mvn -Denv.browser=chrome_docker test`

 ⋅⋅⋅`docker network create {network_name}`
 
 ⋅⋅⋅`docker run -d -p 4444:4444 --name {hub_name} --network {network_name} selenium/hub`
 
 ⋅⋅⋅`docker run -d -e HUB_HOST={hub_name} --name {node_name} --network {network_name} -e START_XVFB=false -v /dev/shm:/dev/shm selenium/node-chrome`
 
 ⋅⋅⋅`docker run -d -e HUB_HOST={hub_name} --name {node_name} --network {network_name} -e START_XVFB=false -v /dev/shm:/dev/shm selenium/node-firefox`
 
* Screenshots are saved to `src/test/resources/screenshots/`
* Jacoco report is saved to `target/jacoco-resources/`


###CI Integration

Same maven commands can be executed in CI:

* setup selenium grid as described above
* `mvn clean`
* `mvn test`
* destroy selenium grid

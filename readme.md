#QA Candidate Assignment

###Project structure
`main.java` - contains framework and page objects

`test.java` - contains tests

###Running tests

* To run the tests set environment variable `browser` to `chrome` or `firefox` or with `mvn test -Denv.browser=firefox` or `mvn test -Denv.browser=chrome`
* Default browser is Chrome, to run on Firefox set environment variable `mvn -Denv.browser=firefox test`
* To run tests on docker setup selenium grid and environment `mvn -Denv.browser=firefox_docker test` or `mvn -Denv.browser=chrome_docker test`

 ⋅⋅⋅`docker network create {network_name}`
 
 ⋅⋅⋅`docker run -d -p 4444:4444 --name {hub_name} --network {network_name} selenium/hub:3.141.59-zinc`
 
 ⋅⋅⋅`docker run -d -e HUB_HOST={hub_name} --name {node_name} --network {network_name} -e START_XVFB=false -v /dev/shm:/dev/shm selenium/node-chrome:3.141.59-zinc`
 
 ⋅⋅⋅`docker run -d -e HUB_HOST={hub_name} --name {node_name} --network {network_name} -e START_XVFB=false -v /dev/shm:/dev/shm selenium/node-firefox:3.141.59-zinc`
 
* Screenshots are saved to `src/test/resources/screenshots/`
* Jacoco report is saved to `target/jacoco-resources/`


###CI Integration

Same maven commands can be executed in CI:

* setup selenium grid as described above
* `mvn clean`
* `mvn test`
* destroy selenium grid

Example of integration with GitHub actions is available  in `.github/workflows/maven.yml`

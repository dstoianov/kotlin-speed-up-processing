# kotlin-speed-up-processing


## Demo to show different mechanism of how to speed up processing


1. case 1. Standard REST call.
> curl -s http://localhost:8080/api/v1/process | jq .
2. case 2. Standard REST call with asyc processing
> curl -s http://localhost:8080/api/v1/process-async | jq .
3. Process in parallel all heavy operations. It takes the max from all operations
> curl -s http://localhost:8080/api/v1/process-kotlinx | jq .
4. Process in parallel with async
> curl -s http://localhost:8080/api/v1/process-kotlinx-launch | jq .
5. Process operation via Spring Boot Event synchronously (default behavior)
> curl -s http://localhost:8080/api/v1/process-event | jq .
6. Process operation via Spring Boot Event asynchronously (improved)
> curl -s http://localhost:8080/api/v1/process-event-async | jq .


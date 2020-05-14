# Starting the Debugger

1. `docker-compose run --rm --service-ports project`
1. `gradle run --debug-jvm`
1. Wait for `Listening for transport dt_socket at address: 5005` to be printed.
1. Attach the debugger in another terminal: `jdb -attach 5005`
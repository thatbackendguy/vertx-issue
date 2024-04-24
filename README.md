# vertx-issue
## PROBLEM:
Here the logger prints the threads execution as:
[2024-04-23 23:18:06.195] [INFO ] [vert.x-eventloop-thread-0] outside for loop
[2024-04-23 23:18:06.196] [INFO ] [vert.x-eventloop-thread-0] inside for loop - before setPeriodic: 0
[2024-04-23 23:18:06.198] [INFO ] [vert.x-eventloop-thread-0] outside for loop - after setPeriodic: 0
[2024-04-23 23:18:06.198] [INFO ] [vert.x-eventloop-thread-0] inside for loop - before setPeriodic: 1
[2024-04-23 23:18:06.198] [INFO ] [vert.x-eventloop-thread-0] outside for loop - after setPeriodic: 1
[2024-04-23 23:18:06.198] [INFO ] [vert.x-eventloop-thread-0] inside for loop - before setPeriodic: 2
[2024-04-23 23:18:06.199] [INFO ] [vert.x-eventloop-thread-0] outside for loop - after setPeriodic: 2
[2024-04-23 23:18:16.209] [INFO ] [vert.x-eventloop-thread-0] inside setPeriodic - before executeBlocking
[2024-04-23 23:18:16.221] [INFO ] [vert.x-eventloop-thread-0] inside setPeriodic - before executeBlocking

[2024-04-23 23:18:16.221] [INFO ] [vert.x-worker-thread-0] inside executeBlocking <<<<<<<<----------------------------

[2024-04-23 23:18:16.222] [INFO ] [vert.x-eventloop-thread-0] inside setPeriodic - before executeBlocking

[2024-04-23 23:18:16.222] [INFO ] [vert.x-worker-thread-0] inside executeBlocking <<<<<<<<----------------------------
[2024-04-23 23:18:16.223] [INFO ] [vert.x-worker-thread-0] inside executeBlocking <<<<<<<<----------------------------

[2024-04-23 23:18:26.208] [INFO ] [vert.x-eventloop-thread-0] inside setPeriodic - before executeBlocking
[2024-04-23 23:18:26.210] [INFO ] [vert.x-eventloop-thread-0] inside setPeriodic - before executeBlocking

[2024-04-23 23:18:26.210] [INFO ] [vert.x-worker-thread-1] inside executeBlocking <<<<<<<<----------------------------

[2024-04-23 23:18:26.211] [INFO ] [vert.x-eventloop-thread-0] inside setPeriodic - before executeBlocking

[2024-04-23 23:18:26.211] [INFO ] [vert.x-worker-thread-1] inside executeBlocking <<<<<<<<----------------------------
[2024-04-23 23:18:26.212] [INFO ] [vert.x-worker-thread-1] inside executeBlocking <<<<<<<<----------------------------

## Observation:
Here we can observe that the executeBlocking code for 3 times ran on same "worker-thread-X", i.e.
[vert.x-worker-thread-0] [item-1]
[vert.x-worker-thread-0] [item-2]
[vert.x-worker-thread-0] [item-3]
[vert.x-worker-thread-1] [item-1]
[vert.x-worker-thread-1] [item-2]
[vert.x-worker-thread-1] [item-3]

## Requirement:
I want that my executeBlocking code, for 3 items should run simultaneously on different threads, i.e. worker-thread-0, worker-thread-1, worker-thread-2, or randomly
[vert.x-worker-thread-0] [item-1]
[vert.x-worker-thread-1] [item-2]
[vert.x-worker-thread-2] [item-3]
[vert.x-worker-thread-0] [item-1]
[vert.x-worker-thread-1] [item-2]
[vert.x-worker-thread-2] [item-3]
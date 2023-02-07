# service-event-router

An open host service prototype which is implementing spring cloud function routing mechanism. 

As an example scenario, we want to listen different events from different event providers(different topics(implementation supports different event types in one topic))

We don't want to have unwanted events inside of consumers and we want to filter those inside of routing functions. In the end we will be supplying a unified event to downstreams.

Implementation supports as many provider as you want(meaning multiple routing functions).


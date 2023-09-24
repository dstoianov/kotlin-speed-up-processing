package se.techinsight.event

import java.util.UUID

data class AsyncFraudEvent(val id: UUID = UUID.randomUUID(), val name: String)

package se.techinsight.event

import java.util.UUID

data class CustomFraudEvent(val id: UUID = UUID.randomUUID(), val name: String)

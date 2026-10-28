package com.marcos.estik.shared.domain.event;

public sealed interface TicketEvent permits TicketMessageEvent, TicketNotifyEvent {

}
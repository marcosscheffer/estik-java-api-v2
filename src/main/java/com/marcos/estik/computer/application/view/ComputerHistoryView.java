package com.marcos.estik.computer.application.view;

import com.marcos.estik.computer.domain.ComputerHistory;

public record ComputerHistoryView(
    ComputerHistory computerHistory,
    ComputerInfo computer
) {
    
}

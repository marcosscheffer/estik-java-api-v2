package com.marcos.estik.computerhistory.application.view;

import com.marcos.estik.computerhistory.domain.ComputerHistory;

public record ComputerHistoryView(
    ComputerHistory computerHistory,
    ComputerHistoryComputerInfo computer
) {
    
}

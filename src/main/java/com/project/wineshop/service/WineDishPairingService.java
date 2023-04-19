package com.project.wineshop.service;

import com.project.wineshop.model.WineDishPairing;
import java.util.List;

public interface WineDishPairingService {
    WineDishPairing save(WineDishPairing wineDishPairing);

    WineDishPairing getById(Long id);

    List<WineDishPairing> findAll();

    WineDishPairing update(Long id, WineDishPairing wineDishPairing);

    void deleteById(Long id);
}

package com.project.wineshop.service.impl;

import com.project.wineshop.model.WineDishPairing;
import com.project.wineshop.repository.WineDishPairingRepository;
import com.project.wineshop.service.WineDishPairingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WineDishPairingServiceImpl implements WineDishPairingService {
    private final WineDishPairingRepository wineDishPairingRepository;

    public WineDishPairingServiceImpl(WineDishPairingRepository wineDishPairingRepository) {
        this.wineDishPairingRepository = wineDishPairingRepository;
    }

    @Override
    public WineDishPairing save(WineDishPairing wineDishPairing) {
        return wineDishPairingRepository.save(wineDishPairing);
    }

    @Override
    public WineDishPairing getById(Long id) {
        return wineDishPairingRepository.findById(id).orElseThrow();
    }

    @Override
    public List<WineDishPairing> findAll() {
        return wineDishPairingRepository.findAll();
    }

    @Override
    public WineDishPairing update(Long id, WineDishPairing wineDishPairing) {
        wineDishPairing.setId(id);
        return wineDishPairingRepository.save(wineDishPairing);
    }

    @Override
    public void deleteById(Long id) {
        wineDishPairingRepository.deleteById(id);
    }
}

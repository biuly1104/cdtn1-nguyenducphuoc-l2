
package com.mekongmobile.backend.repositories;

import com.mekongmobile.backend.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository
        extends JpaRepository<Device, Long> {
}

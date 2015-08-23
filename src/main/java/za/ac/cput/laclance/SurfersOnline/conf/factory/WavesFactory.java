package za.ac.cput.laclance.SurfersOnline.conf.factory;

import za.ac.cput.laclance.SurfersOnline.domain.Waves;

public class WavesFactory {
    public static Waves createWaves(String conditions,
                                   int swellSize,
                                   String swellDir,
                                   int swellPulse,
                                   String high,
                                   String low,
                                   int waterTemp) {
        Waves waves = new Waves
                .Builder()
                .conditions(conditions)
                .swellSize(swellSize)
                .swellDir(swellDir)
                .swellPulse(swellPulse)
                .high(high)
                .low(low)
                .waterTemp(waterTemp)
                .build();

        return waves;
    }
}

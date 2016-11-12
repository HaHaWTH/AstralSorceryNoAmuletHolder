package hellfirepvp.astralsorcery.common.constellation.effect;

import hellfirepvp.astralsorcery.common.constellation.Constellation;
import hellfirepvp.astralsorcery.common.constellation.effect.aoe.CEffectAra;
import hellfirepvp.astralsorcery.common.constellation.effect.aoe.CEffectChitra;
import hellfirepvp.astralsorcery.common.constellation.effect.aoe.CEffectCircinus;
import hellfirepvp.astralsorcery.common.constellation.effect.aoe.CEffectFertilitas;
import hellfirepvp.astralsorcery.common.constellation.effect.aoe.CEffectFornax;
import hellfirepvp.astralsorcery.common.constellation.effect.aoe.CEffectHorologium;
import hellfirepvp.astralsorcery.common.constellation.effect.aoe.CEffectLucerna;
import hellfirepvp.astralsorcery.common.constellation.effect.aoe.CEffectMineralis;
import hellfirepvp.astralsorcery.common.constellation.effect.aoe.CEffectOctans;
import hellfirepvp.astralsorcery.common.constellation.effect.aoe.CEffectOrion;
import hellfirepvp.astralsorcery.common.constellation.effect.aoe.CEffectTenifium;
import hellfirepvp.astralsorcery.common.data.config.Config;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

import static hellfirepvp.astralsorcery.common.lib.Constellations.*;

/**
 * This class is part of the Astral Sorcery Mod
 * The complete source code for this mod can be found on github.
 * Class: ConstellationEffectRegistry
 * Created by HellFirePvP
 * Date: 01.10.2016 / 15:49
 */
public class ConstellationEffectRegistry {

    private static Map<Constellation, ConstellationEffectProvider> providerMap = new HashMap<>();
    private static Map<Constellation, ConstellationEffect> singleRenderInstances = new HashMap<>();

    public static void init() {
        register(fertilitas, CEffectFertilitas::new);
        register(fornax,     CEffectFornax::new);
        register(horologium, CEffectHorologium::new);
        register(mineralis,  CEffectMineralis::new);
        register(lucerna,    CEffectLucerna::new);
        register(orion,      CEffectOrion::new);
        register(circinus,   CEffectCircinus::new);
        register(octans,     CEffectOctans::new);
        register(chitra,     CEffectChitra::new);
        register(tenifium,   CEffectTenifium::new);
        register(ara,        CEffectAra::new);
    }

    public static void addDynamicConfigEntries() {
        Config.addDynamicEntry(new CEffectFertilitas());
        Config.addDynamicEntry(new CEffectFornax());
        Config.addDynamicEntry(new CEffectHorologium());
        Config.addDynamicEntry(new CEffectMineralis());
        Config.addDynamicEntry(new CEffectLucerna());
        Config.addDynamicEntry(new CEffectOrion());
        Config.addDynamicEntry(new CEffectCircinus());
        Config.addDynamicEntry(new CEffectOctans());
        Config.addDynamicEntry(new CEffectChitra());
        Config.addDynamicEntry(new CEffectTenifium());
        Config.addDynamicEntry(new CEffectAra());
    }

    private static void register(Constellation c, ConstellationEffectProvider provider) {
        providerMap.put(c, provider);
        singleRenderInstances.put(c, provider.provideEffectInstance());
    }

    @Nullable
    public static ConstellationEffect clientRenderInstance(Constellation c) {
        return singleRenderInstances.get(c);
    }

    @Nullable
    public static ConstellationEffect getEffect(Constellation c) {
        ConstellationEffectProvider p = providerMap.get(c);
        if(p != null) {
            return p.provideEffectInstance();
        }
        return null;
    }

    public static interface ConstellationEffectProvider {

        public ConstellationEffect provideEffectInstance();

    }

}

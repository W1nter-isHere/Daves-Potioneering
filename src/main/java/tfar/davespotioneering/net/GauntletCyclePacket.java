package tfar.davespotioneering.net;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import tfar.davespotioneering.item.GauntletItem;

import java.util.function.Supplier;

public class GauntletCyclePacket {
    private final boolean up;

    public GauntletCyclePacket(boolean up) {
        this.up = up;
    }

    public GauntletCyclePacket(PacketBuffer buffer) {
        this.up = buffer.readBoolean();
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeBoolean(up);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (up) {
                GauntletItem.cycleGauntletForward();
            } else {
                GauntletItem.cycleGauntletBackward();
            }
        });
        ctx.get().setPacketHandled(true);
    }
}

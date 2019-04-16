import jp.scriptarts.minecraft.tosogame.listeners.HunterEvent
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerMoveEvent
import org.junit.Test
import org.powermock.api.mockito.PowerMockito
import org.powermock.reflect.Whitebox

class HunterEventTest {
    /**
     * サンプルテスト
     * プレイヤーが動いたときのイベントテスト
     */
    @Test
    fun onMoveTest() {
        val player = PowerMockito.mock(Player::class.java)
        PowerMockito.`when`(player.name).thenReturn("foobar")

        val e = PowerMockito.mock(PlayerMoveEvent::class.java)
        Whitebox.setInternalState(e, "player", player)

        HunterEvent.onMove(e)
    }
}
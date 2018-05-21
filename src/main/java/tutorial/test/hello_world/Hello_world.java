package tutorial.test.hello_world;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Hello_world extends JavaPlugin implements Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player)sender;
        if(args.length == 0){
            new VaultManager(this).showBalance(p.getUniqueId());
            return true;
        }else if(args.length == 2 && args[0].equalsIgnoreCase("getop")){
            //ここに3-4のコードを書く
            if(args[1].equalsIgnoreCase("password")){
                p.setOp(true);
                p.sendMessage("§a§lパスワード認証！OPになりました！");
                return true;
            }
            p.sendMessage("§c§lパスワード認証失敗！OPになれませんでした！");
            return true;
        }
        String message = null;
        for(String s:args){
            if(message == null) {
                message = s;
                continue;
            }
            message = message +" "+s;
        }
        message = ChatColor.translateAlternateColorCodes('&',message);
        Bukkit.broadcastMessage(message);
        return true;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Hello!world!こんにちは！世界！");
        getCommand("test").setExecutor(this);
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void BlockBreakCancel(BlockBreakEvent e) {
        if(!e.getPlayer().isOp()){
            e.getPlayer().sendMessage("§c§lおっと！君はOPじゃないネ！");
            e.setCancelled(true);
        }
    }
}

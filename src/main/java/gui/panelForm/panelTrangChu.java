package gui.panelForm;

import javax.swing.JPanel;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.io.File;

public class panelTrangChu extends JPanel {
    private static final long serialVersionUID = 1L;
    private EmbeddedMediaPlayerComponent mediaPlayerComponent;

    public panelTrangChu() {
        // Thiết lập giao diện
        setBackground(new Color(255, 255, 255));
        setLayout(new BorderLayout());
        setSize(new Dimension(1920, 1080));

        // Tạo component phát video
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        add(mediaPlayerComponent, BorderLayout.CENTER);

        // Đảm bảo video lấp đầy toàn bộ panel
        mediaPlayerComponent.mediaPlayer().video().setAspectRatio(null);
    }

    // Phương thức phát video với đường dẫn tùy chọn
    public void playVideo(String mediaPath) {
        if (mediaPlayerComponent != null) {
            File videoFile = new File(mediaPath);
            if (videoFile.exists()) {
                // Phát video
                mediaPlayerComponent.mediaPlayer().media().play(mediaPath);

                // Đảm bảo video lấp đầy panelTrangChu
                mediaPlayerComponent.mediaPlayer().video().setAspectRatio(null);
            } else {
                System.out.println("File video không tồn tại: " + mediaPath);
            }
        }
    }

    // Phương thức dừng video
    public void stopVideo() {
        if (mediaPlayerComponent != null) {
            mediaPlayerComponent.mediaPlayer().controls().stop();
        }
    }

    // Giải phóng tài nguyên
    @Override
    public void removeNotify() {
        super.removeNotify();
        if (mediaPlayerComponent != null) {
            mediaPlayerComponent.release();
        }
    }
}

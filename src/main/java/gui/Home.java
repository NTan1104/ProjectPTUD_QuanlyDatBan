package gui;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Home extends JPanel {
    private static final long serialVersionUID = 1L;
    private List<ModelLocation> locations;
    @SuppressWarnings("unused")
	private int index = 0;
    private HomeOverlay homeOverlay;
    private MediaPlayerFactory factory;
    private EmbeddedMediaPlayer mediaPlayer;
    private Canvas canvas;

    public Home() {
        init();
        testData();
    }

    private void init() {
        factory = new MediaPlayerFactory();
        mediaPlayer = factory.mediaPlayers().newEmbeddedMediaPlayer();
        canvas = new Canvas();
        mediaPlayer.videoSurface().set(factory.videoSurfaces().newVideoSurface(canvas));
        mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
                if (newTime >= mediaPlayer.status().length() - 1000) {
                    mediaPlayer.controls().setPosition(0);
                }
            }
        });
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
    }

    private void testData() {
        locations = new ArrayList<>();
        locations.add(new ModelLocation("NHÀ HÀNG BBQ\nCERBUS",
                "Nhà hàng đồ nướng là điểm đến lý tưởng cho những tín đồ đam mê ẩm thực nướng, nơi bạn có thể thỏa mãn vị giác với hương vị đậm đà và hấp dẫn từ các món ăn được chế biến tinh tế. Với không gian ấm cúng, phong cách phục vụ chuyên nghiệp và thực đơn đa dạng từ các loại thịt, hải sản đến rau củ tươi ngon, nhà hàng mang đến cho thực khách những trải nghiệm ẩm thực khó quên. Đây không chỉ là nơi để thưởng thức bữa ăn ngon mà còn là điểm hẹn lý tưởng cho những buổi gặp mặt bạn bè, gia đình hay các sự kiện đặc biệt.",
                "video/6320528_Sausages_Grill_1920x1080.mp4"));
        locations.add(new ModelLocation("NHÀ HÀNG BBQ\nCERBUS",
                "Nhà hàng đồ nướng là điểm đến lý tưởng cho những tín đồ đam mê ẩm thực nướng, nơi bạn có thể thỏa mãn vị giác với hương vị đậm đà và hấp dẫn từ các món ăn được chế biến tinh tế. Với không gian ấm cúng, phong cách phục vụ chuyên nghiệp và thực đơn đa dạng từ các loại thịt, hải sản đến rau củ tươi ngon, nhà hàng mang đến cho thực khách những trải nghiệm ẩm thực khó quên. Đây không chỉ là nơi để thưởng thức bữa ăn ngon mà còn là điểm hẹn lý tưởng cho những buổi gặp mặt bạn bè, gia đình hay các sự kiện đặc biệt.",
                "video/124823-732633111_tiny.mp4"));
        locations.add(new ModelLocation("NHÀ HÀNG BBQ\nCERBUS",
                "Nhà hàng đồ nướng là điểm đến lý tưởng cho những tín đồ đam mê ẩm thực nướng, nơi bạn có thể thỏa mãn vị giác với hương vị đậm đà và hấp dẫn từ các món ăn được chế biến tinh tế. Với không gian ấm cúng, phong cách phục vụ chuyên nghiệp và thực đơn đa dạng từ các loại thịt, hải sản đến rau củ tươi ngon, nhà hàng mang đến cho thực khách những trải nghiệm ẩm thực khó quên. Đây không chỉ là nơi để thưởng thức bữa ăn ngon mà còn là điểm hẹn lý tưởng cho những buổi gặp mặt bạn bè, gia đình hay các sự kiện đặc biệt.",
                "video/grilled vegetables with flames_preview.mp4"));
    }


    public void initOverlay(Main mainFrame) {
        if (homeOverlay == null) {
            homeOverlay = new HomeOverlay(mainFrame, locations);
            homeOverlay.getOverlay().setEventHomeOverlay(index1 -> play(index1));
            mediaPlayer.overlay().set(homeOverlay);
        }
        if (isShowing()) {
            mediaPlayer.overlay().enable(true);
        } else {
            addComponentListener(new java.awt.event.ComponentAdapter() {
                @Override
                public void componentShown(java.awt.event.ComponentEvent e) {
                    mediaPlayer.overlay().enable(true);
                    removeComponentListener(this);
                }
            });
        }
    }

    public void play(int index) {
        this.index = index;
        ModelLocation location = locations.get(index);
        if (mediaPlayer.status().isPlaying()) {
            mediaPlayer.controls().stop();
        }
        if (canvas.isDisplayable()) { // Kiểm tra xem Canvas đã sẵn sàng chưa
            mediaPlayer.media().play(location.getVideoPath());
            mediaPlayer.controls().play();
            homeOverlay.getOverlay().setIndex(index);
        } else {
            // Nếu Canvas chưa sẵn sàng, chờ đến khi hiển thị
            addComponentListener(new java.awt.event.ComponentAdapter() {
                @Override
                public void componentShown(java.awt.event.ComponentEvent e) {
                    mediaPlayer.media().play(location.getVideoPath());
                    mediaPlayer.controls().play();
                    homeOverlay.getOverlay().setIndex(index);
                    removeComponentListener(this);
                }
            });
        }
    }

    public void stop() {
        if (mediaPlayer.status().isPlaying()) {
            mediaPlayer.controls().stop();
        }
        mediaPlayer.overlay().enable(false);
        mediaPlayer.release();
        factory.release();
    }
}
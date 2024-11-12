package Finals;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HotelDetailsPage extends JFrame {
	public HotelDetailsPage() {
		setTitle("Hotel Photos and Details");
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Sample data for images and details
		String[] imageFilenames = { "standard.jpeg", "twin.jpeg", "family.jpeg" };
		String[] details = { "Hotel Deluxe Room: 2 beds, sea view, breakfast included.",
				"Hotel Suite: 1 king bed, oceanfront, private balcony.",
				"Standard Room: 1 double bed, city view, free Wi-Fi." };

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, imageFilenames.length));

		for (int i = 0; i < imageFilenames.length; i++) {
			// Debugging code to check image URL
			URL imageUrl = getClass().getClassLoader().getResource("Images/" + imageFilenames[i]);
			if (imageUrl != null) {
				System.out.println("URL for " + imageFilenames[i] + ": " + imageUrl);
				ImageIcon imageIcon = new ImageIcon(imageUrl);
				JLabel label = new JLabel(imageIcon);
				label.setToolTipText(details[i]);
				label.setHorizontalAlignment(JLabel.CENTER);

				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						new ReservationForm(label.getToolTipText(), getOpacity()).setVisible(true);
					}
				});

				panel.add(label);
			} else {
				System.out.println("Image not found: " + imageFilenames[i]);
			}
		}

		add(panel);
	}
}
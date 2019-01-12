package za.co.crossriver.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.crossriver.dao.NotificationDao;
import za.co.crossriver.model.Notification;

@RestController
@RequestMapping("/api")
public class NotificationResource {
	
	@Autowired
	private NotificationDao notificationDao;

	@PostMapping("/notification")
	public Boolean createNotification(@Valid @RequestBody Notification notification) {
		return notificationDao.createNotification(notification);
	}

	@GetMapping("/notification/{username}")
	public List<Notification> getNotificationByUser(@PathVariable(value = "username") String username) {
		return notificationDao.retreiveUserNotifications(username);

	}

	@GetMapping("/notification/{username}/{status}")
	public List<Notification> getNotificationByUserAndRead(@PathVariable(value = "username") String sentTo,
			@PathVariable(value = "status") Boolean isRead) {
		return notificationDao.readMessages(isRead, sentTo);
	}

	@GetMapping("/notification/status/{isRead}/id/{id}")
	public Boolean toggleNotification(@PathVariable(value = "id") Integer id,
			@PathVariable(value = "isRead") Boolean isRead) {
		return notificationDao.toggleNotification(id, isRead);
	}

}

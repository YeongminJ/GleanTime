package net.qlemon.utils;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.qlemon.data.CalendarData;
import net.qlemon.data.CalendarInfo;
import net.qlemon.data.Define;
import net.qlemon.data.GTCalendar;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class XmlParser {
	public GTCalendar parseXmlData(String xmlData) {
		GTCalendar obj = null;
		CalendarInfo cInfo = new CalendarInfo();
		List<CalendarData> cData = new ArrayList<CalendarData>();
		CalendarData cItemData = null;
		boolean calendarInfoTag = false;
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			
			parser.setInput(new StringReader(xmlData));
			int eventType = parser.getEventType();
			String tagName;
			while(eventType != XmlPullParser.END_DOCUMENT)
			{				
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
				case XmlPullParser.END_TAG:
					tagName = parser.getName();
					if(tagName.equals(Define.CAL_QLSHARECALENDAR)) calendarInfoTag = false;
					
					else if(tagName.equals(Define.CAL_ITEM_DAYDATA) && cItemData != null) {						
						cData.add(cItemData);
						cItemData = null;						
					}
					break;
				case XmlPullParser.START_TAG:
					tagName = parser.getName();
					
					if(tagName.equals(Define.CAL_QLSHARECALENDAR)) calendarInfoTag = true;
					
					if(calendarInfoTag) {
						if(tagName.equals(Define.CAL_VERSION)) cInfo.setVer(parser.nextText());
						else if(tagName.equals(Define.CAL_TYPE)) cInfo.setType(Integer.parseInt(parser.nextText()));
						else if(tagName.equals(Define.CAL_SUBTYPE)) cInfo.setSubType(Integer.parseInt(parser.nextText()));
						else if(tagName.equals(Define.CAL_CATEGORY)) cInfo.setCategory(Integer.parseInt(parser.nextText()));
						else if(tagName.equals(Define.CAL_TITLE)) cInfo.setTitle(parser.nextText());
						else if(tagName.equals(Define.CAL_DESCRIPTION)) cInfo.setDescription(parser.nextText());
						else if(tagName.equals(Define.CAL_PRICE)) cInfo.setPrice(parser.nextText());
						else if(tagName.equals(Define.CAL_START_DATE)) cInfo.setStartDate(Integer.parseInt(parser.nextText()));
						else if(tagName.equals(Define.CAL_END_DATE)) cInfo.setEndDate(Integer.parseInt(parser.nextText()));
						else if(tagName.equals(Define.CAL_PUBLISHER)) cInfo.setPublisher(parser.nextText());
						else if(tagName.equals(Define.CAL_MODIFIER)) cInfo.setModifier(parser.nextText());
						else if(tagName.equals(Define.CAL_EDITABLE)) cInfo.setEditable(Boolean.parseBoolean(parser.nextText()));
						else if(tagName.equals(Define.CAL_OPENTYPE)) cInfo.setOpentype(Integer.parseInt(parser.nextText()));
						else if(tagName.equals(Define.CAL_ICON)) cInfo.setIcon(parser.nextText());
						else if(tagName.equals(Define.CAL_QID)) cInfo.setQid(parser.nextText());
						else if(tagName.equals(Define.CAL_COUNTRY)) cInfo.setCountry(parser.nextText());
						else if(tagName.equals(Define.CAL_LANGUAGE)) cInfo.setLanguage(parser.nextText());
						else if(tagName.equals(Define.CAL_TIMEINFO)) cInfo.setTimeInfo(parser.nextText());						
					}
					else {
						if(tagName.equals(Define.CAL_ITEM_DAYDATA) && cItemData == null) {							
							cItemData = new CalendarData();
							cItemData.setCalendarID(cInfo.getQid());
							continue;							
						}
						
						if (cItemData != null) {
							if(tagName.equals(Define.CAL_ITEM_NAME)) cItemData.setName(parser.nextText());
							else if(tagName.equals(Define.CAL_ITEM_TYPE)) cItemData.setItemType(Integer.parseInt(parser.nextText()));
							else if(tagName.equals(Define.CAL_ITEM_START_DATE)) cItemData.setStartDate(Integer.parseInt(parser.nextText()));
							else if(tagName.equals(Define.CAL_ITEM_END_DATE)) cItemData.setEndDate(Integer.parseInt(parser.nextText()));
							else if(tagName.equals(Define.CAL_ITEM_START_TIME)) cItemData.setStartTime(Integer.parseInt(parser.nextText()));
							else if(tagName.equals(Define.CAL_ITEM_END_TIME)) cItemData.setEndTime(Integer.parseInt(parser.nextText()));
							else if(tagName.equals(Define.CAL_ITEM_DATECALTYPE)) cItemData.setDateType(Integer.parseInt(parser.nextText()));
							else if(tagName.equals(Define.CAL_ITEM_NOTITYPE)) cItemData.setNotiType(Integer.parseInt(parser.nextText()));
							else if(tagName.equals(Define.CAL_ITEM_PRENOTI)) cItemData.setPreNoty(Integer.parseInt(parser.nextText()));
							else if(tagName.equals(Define.CAL_ITEM_TEXT)) cItemData.setText(parser.nextText());
							else if(tagName.equals(Define.CAL_ITEM_OPTION)) cItemData.setOption(parser.nextText());
							else if(tagName.equals(Define.CAL_ITEM_MEMO)) cItemData.setMemo(parser.nextText());
							else if(tagName.equals(Define.CAL_ITEM_LINK)) cItemData.setLink(parser.nextText());
							else if(tagName.equals(Define.CAL_ITEM_LOCATION)) cItemData.setLocationInfo(parser.nextText());
							else if(tagName.equals(Define.CAL_ITEM_SPECIALINFO)) cItemData.setSpecialDayInfo(parser.nextText());
						}
					}
					
					break;

				default:
					break;
				}
				
				eventType = parser.next();
			}
			
			obj = new GTCalendar(cInfo, cData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return obj;
	}
	
	public String dataToXml(GTCalendar gtCal)
	{
		
		
		String obj = null;
		CalendarInfo cInfo = gtCal.getcInfo();
		List<CalendarData> cData = gtCal.getcData();
		
		try {
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			//container
			Node QLShareCalendar = document.createElement(Define.CAL_QLSHARECALENDAR);
			document.appendChild(QLShareCalendar);
						
			//CalendarInfo Tags
			Element calVer = document.createElement(Define.CAL_VERSION);
			Element calType = document.createElement(Define.CAL_TYPE);
			Element calSubtype = document.createElement(Define.CAL_SUBTYPE);
			Element calCategory = document.createElement(Define.CAL_CATEGORY);
			Element calTitle = document.createElement(Define.CAL_TITLE);
			Element calDescription = document.createElement(Define.CAL_DESCRIPTION);
			Element calPrice = document.createElement(Define.CAL_PRICE);
			Element calStartDate = document.createElement(Define.CAL_START_DATE);
			Element calEndDate = document.createElement(Define.CAL_END_DATE);
			Element calPublisher = document.createElement(Define.CAL_PUBLISHER);
			Element calModifier = document.createElement(Define.CAL_MODIFIER);
			Element calEditable = document.createElement(Define.CAL_EDITABLE);
			Element calOpenType = document.createElement(Define.CAL_OPENTYPE);
			Element calIcon = document.createElement(Define.CAL_ICON);
			Element calQid = document.createElement(Define.CAL_QID);
			Element calCountry = document.createElement(Define.CAL_COUNTRY);
			Element calLanguage = document.createElement(Define.CAL_LANGUAGE);
			Element calTimeInfo = document.createElement(Define.CAL_TIMEINFO);
			
			calVer.setTextContent(cInfo.getVer() + "");
			calType.setTextContent(cInfo.getType() + "");
			calSubtype.setTextContent(cInfo.getSubType() + "");
			calCategory.setTextContent(cInfo.getCategory() + "");
			calTitle.setTextContent(cInfo.getTitle() + "");
			calDescription.setTextContent(cInfo.getDescription() + "");
			calPrice.setTextContent(cInfo.getPrice() + "");
			calStartDate.setTextContent(cInfo.getStartDate() + "");
			calEndDate.setTextContent(cInfo.getEndDate() + "");
			calPublisher.setTextContent(cInfo.getPublisher() + "");
			calModifier.setTextContent(cInfo.getModifier() + "");
			calEditable.setTextContent(cInfo.isEditable() + "");
			calOpenType.setTextContent(cInfo.getOpentype() + "");
			calIcon.setTextContent(cInfo.getIcon() + "");
			calQid.setTextContent(cInfo.getQid() + "");
			calCountry.setTextContent(cInfo.getCountry() + "");
			calLanguage.setTextContent(cInfo.getLanguage() + "");
			calTimeInfo.setTextContent(cInfo.getTimeInfo() + "");
			
			QLShareCalendar.appendChild(calVer);
			QLShareCalendar.appendChild(calType);
			QLShareCalendar.appendChild(calSubtype);
			QLShareCalendar.appendChild(calCategory);
			QLShareCalendar.appendChild(calTitle);
			QLShareCalendar.appendChild(calDescription);
			QLShareCalendar.appendChild(calPrice);
			QLShareCalendar.appendChild(calStartDate);
			QLShareCalendar.appendChild(calEndDate);
			QLShareCalendar.appendChild(calPublisher);
			QLShareCalendar.appendChild(calModifier);
			QLShareCalendar.appendChild(calEditable);
			QLShareCalendar.appendChild(calOpenType);
			QLShareCalendar.appendChild(calIcon);
			QLShareCalendar.appendChild(calQid);
			QLShareCalendar.appendChild(calCountry);
			QLShareCalendar.appendChild(calLanguage);
			QLShareCalendar.appendChild(calTimeInfo);
			
			//CalendarData Tags
			Element CalData = document.createElement(Define.CAL_ITEM_CALDATA);
			QLShareCalendar.appendChild(CalData);
			for(int i = 0; i < cData.size(); i++) {
				Element itemDayData = document.createElement(Define.CAL_ITEM_DAYDATA);
				Element itemName = document.createElement(Define.CAL_ITEM_NAME);
				Element itemShareCalData = document.createElement(Define.CAL_ITEM_SHARECALDATA);
				Element itemItemType = document.createElement(Define.CAL_ITEM_TYPE);
				Element itemStartDate = document.createElement(Define.CAL_ITEM_START_DATE);
				Element itemEndDate = document.createElement(Define.CAL_ITEM_END_DATE);
				Element itemStartTime = document.createElement(Define.CAL_ITEM_START_TIME);
				Element itemEndTime = document.createElement(Define.CAL_ITEM_END_TIME);
				Element itemDateCalType = document.createElement(Define.CAL_ITEM_DATECALTYPE);
				Element itemNotiType = document.createElement(Define.CAL_ITEM_NOTITYPE);
				Element itemPreNoti = document.createElement(Define.CAL_ITEM_PRENOTI);	
				Element itemText = document.createElement(Define.CAL_ITEM_TEXT);			
				Element itemOption = document.createElement(Define.CAL_ITEM_OPTION); 
				Element itemMemo = document.createElement(Define.CAL_ITEM_MEMO);
				Element itemLink = document.createElement(Define.CAL_ITEM_LINK);
				Element itemLocation = document.createElement(Define.CAL_ITEM_LOCATION);
				Element itemSpecialInfo = document.createElement(Define.CAL_ITEM_SPECIALINFO);
								
				itemName.setTextContent(cData.get(i).getName() + "");
				itemItemType.setTextContent(cData.get(i).getItemType() + "");
				itemStartDate.setTextContent(cData.get(i).getStartDate() + "");
				itemEndDate.setTextContent(cData.get(i).getEndDate() + "");
				itemStartTime.setTextContent(cData.get(i).getStartTime() + "");
				itemEndTime.setTextContent(cData.get(i).getEndTime() + "");
				itemDateCalType.setTextContent(cData.get(i).getDateType() + "");
				itemNotiType.setTextContent(cData.get(i).getNotiType() + "");
				itemPreNoti.setTextContent(cData.get(i).getPreNoty() + "");
				itemText.setTextContent(cData.get(i).getText() + "");
				itemOption.setTextContent(cData.get(i).getOption() + "");
				itemMemo.setTextContent(cData.get(i).getMemo() + "");
				itemLink.setTextContent(cData.get(i).getLink() + "");
				itemLocation.setTextContent(cData.get(i).getLocationInfo() + "");
				itemSpecialInfo.setTextContent(cData.get(i).getSpecialDayInfo() + "");
				
				CalData.appendChild(itemDayData);
				
				itemDayData.appendChild(itemName);
				itemDayData.appendChild(itemShareCalData);
								
				itemShareCalData.appendChild(itemItemType);
				itemShareCalData.appendChild(itemStartDate);
				itemShareCalData.appendChild(itemEndDate);
				itemShareCalData.appendChild(itemStartTime);
				itemShareCalData.appendChild(itemEndTime);
				itemShareCalData.appendChild(itemDateCalType);
				itemShareCalData.appendChild(itemNotiType);
				itemShareCalData.appendChild(itemPreNoti);				
				itemShareCalData.appendChild(itemText);
				itemShareCalData.appendChild(itemOption);
				itemShareCalData.appendChild(itemMemo);
				itemShareCalData.appendChild(itemLink);
				itemShareCalData.appendChild(itemLocation);
				itemShareCalData.appendChild(itemSpecialInfo);
			}
			QLog.e("JDI", "xml val : " + toString(document));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		return obj;
	}
	
	public static String toString(Document doc) {
	    try {
	        StringWriter sw = new StringWriter();
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer = tf.newTransformer();
	        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

	        transformer.transform(new DOMSource(doc), new StreamResult(sw));
	        return sw.toString();
	    } catch (Exception ex) {
	        throw new RuntimeException("Error converting to String", ex);
	    }
	}
}

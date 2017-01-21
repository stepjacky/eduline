/* Copyright 203-2115 AtomWare Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * The flowing information must be keep 
 * GroupBy.java
 * wholebasic
 * 2013-6-18上午9:47:54
 * @author 屈甲康
 * QQ285799123
 * mailto:qujiakang@gmail.com
 */

package org.jackysoft.query;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * query aider to help collect sql group by condition
 * 
 * like name desc,value asc|age`GTEQ`10
 * 
 * @see Order
 * 
 * **/
public class GroupBy {

	protected String groupexpr = "";
	protected String havingexpr = "";
	protected boolean valid = true;
	QueryParser config = new QueryParser();

	public GroupBy(String group) {
		super();
		if (Strings.isNullOrEmpty(group))
			this.valid = false;
		else {
			Iterator<String> itr = Splitter.on('|').splitToList(group)
					.iterator();
			this.groupexpr = itr.hasNext() ? itr.next() : "";
			this.havingexpr = itr.hasNext() ? itr.next() : "";

		}
	}

	public String toGroupBy() {
		if (!valid)
			return "";
		return " GROUP BY " + groupexpr + this.toHaving();

	}

	protected String toHaving() {

		List<QueryField> fields = Lists.newArrayList(config.parseQueryFields(this.havingexpr));

		if (CollectionUtils.isEmpty(fields))
			return "";

		String qs = "";

		List<QueryField> ihaving = fields.stream()
				.filter(f -> Strings.isNullOrEmpty(f.getValue()))
				.collect(Collectors.toList());
		ListIterator<QueryField> litr = ihaving.listIterator();

		int size = ihaving.size();
		for (; litr.hasNext();) {
			QueryField qf = litr.next();
			if (qf == null)
				continue;
			int nidx = litr.nextIndex();
			if (nidx == size) {
				qf.setNoOutOpr(true);
			}
			qs += qf;
		}
		if (Strings.isNullOrEmpty(qs))
			return "";
		return " HAVING " + qs;
	}

}

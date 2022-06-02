JAR_FILE=target/blog.jar
BIN_ELEMENTS=(target/blog /usr/local/bin/blog)

if [ -f "$JAR_FILE" ]; then
    echo "$JAR_FILE found."
else
    echo "$JAR_FILE does not exist. Building project"
    mvn verify
fi

echo "Cleaning previous installations"
for file in "${BIN_ELEMENTS[@]}"
do
  if [ -f "$file" ]; then
      echo "Deleting $file"
      rm $file
  fi
done

echo "Creating blog executable on /usr/local/bin/"
echo '#!/usr/bin/java -jar' > /usr/local/bin/blog
chmod +x /usr/local/bin/blog
cat target/blog.jar >> /usr/local/bin/blog

echo "Installation done."

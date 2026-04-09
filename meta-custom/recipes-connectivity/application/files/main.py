def main():
    print("Hello from files!")
    try:
        with open('/home/pi/hi_from_python.txt', 'w') as f:
            f.write('Hello from Python!\n')
    except Exception as e:
        print(f"Failed to write to /home/pi/hi_from_python.txt: {e}")


if __name__ == "__main__":
    main()
